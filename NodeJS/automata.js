const crypto = require('crypto');

function sha256(text) {
  return crypto.createHash('sha256').update(text).digest('hex');
}

function generarAutomata(n, k) {
  const nodos = [];
  const startTime = Date.now();

  for (let i = 0; i < n; i++) {
    let partida;
    if (i === 0) {
      partida = sha256(new Date().toISOString());
    } else {
      partida = nodos[i - 1].firma;
    }

    const cuerpo = Array.from({ length: k }, () => Math.floor(Math.random() * 100000) + 1);
    const data = partida + ' ' + cuerpo.join(' ');
    const firma = sha256(data);

    nodos.push({ partida, cuerpo, firma });
  }

  const endTime = Date.now();

  nodos.forEach((nodo, i) => {
    console.log(`Nodo ${i + 1}:`);
    console.log(`  Partida: ${nodo.partida}`);
    console.log(`  Cuerpo : [${nodo.cuerpo.join(', ')}]`);
    console.log(`  Firma  : ${nodo.firma}\n`);
  });

  console.log(`Tiempo de ejecución: ${(endTime - startTime)} ms`);
}

// Cambia estos valores para probar otros escenarios///////////////////////////////////////
generarAutomata(10, 200);
