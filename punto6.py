# solve_ecb_oracle_fast_fixed.py
import requests

BASE = "https://aes.cryptohack.org/ecb_oracle/encrypt/{}/"
BLOCK = 16
MAX_BATCH = BLOCK * 56

# orden de bytes "probables" para cortar antes
PREFERRED = b"etoanihsrdlucgwyfmpbkvjxqz{}_01234567890ETOANIHSRDLUCGWYFMPBKVJXQZ"
CHARSET = list(PREFERRED) + [i for i in range(256) if i not in PREFERRED]

s = requests.Session()
rcount = 0

def enc(pt: bytes) -> bytes:
    global rcount
    rcount += 1
    r = s.get(BASE.format(pt.hex()), timeout=10)
    r.raise_for_status()
    return bytes.fromhex(r.json()["ciphertext"])

def yield_blocks(b: bytes, n: int = BLOCK):
    for i in range(0, len(b), n):
        yield b[i:i+n]

def enc_batched(pt: bytes):
    """Parte el plaintext en trozos seguros de URL y rinde los bloques cifrados 1:1."""
    for i in range((len(pt)-1)//MAX_BATCH + 1):
        chunk = pt[i*MAX_BATCH:(i+1)*MAX_BATCH]
        ct = enc(chunk)
        need = len(chunk)
        for blk in yield_blocks(ct[:need]):
            yield blk

def detect_flag_len():
    """
    Calcula len(flag) sin suposiciones.
    L(m) = 16 * ceil((m + F)/16) con m in [1..16].
    Sea F = 16*q + r, 0<=r<16:
      - Bmin = min L/16 = q + 1
      - Bmax = max L/16 = q + 2  (si r>0)
      - r = #indices con L == Lmax  (si r==0, ese conteo es 16 y r=0)
    """
    lengths = []
    targets = []
    for i in range(BLOCK):
        pt = b"A" * (BLOCK - i)   # m=16..1 (nunca vacío ⇒ sin 404)
        c = enc(pt)
        lengths.append(len(c))
        targets.append(c)

    blocks_cnt = [L // BLOCK for L in lengths]
    Bmin, Bmax = min(blocks_cnt), max(blocks_cnt)
    q = Bmin - 1
    r = blocks_cnt.count(Bmax)
    if r == BLOCK:  # caso F%16==0
        r = 0
    flag_len = 16 * q + r
    return flag_len, targets

def solve():
    flag_len, targets = detect_flag_len()
    print(f"[+] flag_len estimado = {flag_len} bytes")

    flag = b""
    while len(flag) < flag_len:
        # siguiente byte a recuperar
        bidx, off = divmod(len(flag) + 1, BLOCK)           # bloque objetivo y offset
        target_blk = targets[off][bidx*BLOCK:(bidx+1)*BLOCK]

        # últimos 15 bytes de ventana + candidato; todo en un batch
        tail = (b"A"*BLOCK + flag)[-(BLOCK-1):]
        attempts = bytearray()
        for c in CHARSET:
            attempts += (tail + bytes([c]))

        # comparamos cada bloque del batch con el bloque objetivo
        for c, ct_blk in zip(CHARSET, enc_batched(attempts)):
            if ct_blk == target_blk:
                flag += bytes([c])
                print(flag)
                break
        else:
            raise SystemExit("[-] No hubo match (¿red/endpoint?)")

    print(f"[+] FLAG: {flag.decode(errors='ignore')}")
    print(f"[+] Hecho en {rcount} peticiones HTTP.")

if __name__ == "__main__":
    solve()
