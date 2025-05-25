import hashlib
import random
import time
from datetime import datetime

def sha256_hash(data: str) -> str:
    return hashlib.sha256(data.encode()).hexdigest()

def generar_nodos(n, k):
    nodos = []
    inicio = time.time()

    for i in range(n):
        if i == 0:
            partida = sha256_hash(datetime.now().isoformat())
        else:
            partida = nodos[i-1]["firma"]
        
        cuerpo = [random.randint(1, 100000) for _ in range(k)]
        datos_para_firma = partida + " " + " ".join(map(str, cuerpo))
        firma = sha256_hash(datos_para_firma)

        nodo = {
            "partida": partida,
            "cuerpo": cuerpo,
            "firma": firma
        }
        nodos.append(nodo)

    fin = time.time()
    for i, nodo in enumerate(nodos):
        print(f"Nodo {i+1}:")
        print(f"  Partida: {nodo['partida']}")
        print(f"  Cuerpo: {nodo['cuerpo']}")
        print(f"  Firma : {nodo['firma']}\n")

    print(f"Tiempo de ejecución: {fin - inicio:.6f} segundos")

# CAMBIAR VALORES AQUIIIII
generar_nodos(n=3, k=4)
