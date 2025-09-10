class WeightedUndirectedGraph {
    constructor() {
        // lista de adyacencia: nombre -> Map(vecino -> peso)
        this.adj = new Map();
    }

    ensureNode(name) {
        const n = name.trim();
        if (!n) throw new Error('Nombre inválido');
        if (!this.adj.has(n)) this.adj.set(n, new Map());
        return n;
    }

    addNode(name) {
        this.ensureNode(name);
    }

    addEdge(u, v, w) {
        const a = this.ensureNode(u);
        const b = this.ensureNode(v);
        const weight = Number(w);
        if (!Number.isFinite(weight) || weight < 0) throw new Error('Costo inválido');
        if (a === b) throw new Error('No se permiten lazos');
        this.adj.get(a).set(b, weight);
        this.adj.get(b).set(a, weight);
    }

    getNodes() {
        return [...this.adj.keys()].sort();
    }

    toPrettyObject() {
        const obj = {};
        for (const n of this.getNodes()) {
            obj[n] = [...this.adj.get(n).entries()].sort((x,y) => x[0].localeCompare(y[0]))
                .map(([k, w]) => `${k} (${w})`);
        }
        return obj;
    }
}

function primMST(graph, startOpt) {
    const nodes = graph.getNodes();
    if (nodes.length === 0) return { edges: [], cost: 0 };
    const start = startOpt && startOpt.trim() ? startOpt.trim() : nodes[0];
    if (!graph.adj.has(start)) throw new Error('Nodo inicial no existe');

    const visited = new Set();
    const mstEdges = [];
    let totalCost = 0;

    visited.add(start);

    // Min-heap simple con array (suficiente para datasets pequeños en UI)
    const heap = [];
    const push = (item) => { heap.push(item); };
    const popMin = () => {
        if (heap.length === 0) return null;
        let idx = 0;
        for (let i = 1; i < heap.length; i++) if (heap[i].w < heap[idx].w) idx = i;
        return heap.splice(idx, 1)[0];
    };

    const addFrontier = (node) => {
        for (const [nbr, w] of graph.adj.get(node)) {
            if (!visited.has(nbr)) push({ u: node, v: nbr, w });
        }
    };

    addFrontier(start);

    while (mstEdges.length < nodes.length - 1) {
        const best = popMin();
        if (!best) break; // grafo no conexo
        if (visited.has(best.v)) continue;
        visited.add(best.v);
        mstEdges.push(best);
        totalCost += best.w;
        addFrontier(best.v);
    }

    if (mstEdges.length !== nodes.length - 1) {
        throw new Error('El grafo no es conexo; no existe un único MST para todas las estaciones');
    }

    return { edges: mstEdges, cost: totalCost };
}

// --- Estado/UI ---
const g = new WeightedUndirectedGraph();
;['CiudadA','CiudadB','CiudadC','CiudadD'].forEach(n => g.addNode(n));
g.addEdge('CiudadA','CiudadB', 4);
g.addEdge('CiudadA','CiudadC', 3);
g.addEdge('CiudadB','CiudadC', 2);
g.addEdge('CiudadB','CiudadD', 5);
g.addEdge('CiudadC','CiudadD', 7);

const $ = s => document.querySelector(s);

function renderNodes() {
    const ul = $('#nodes-list');
    ul.innerHTML = '';
    g.getNodes().forEach(n => {
        const li = document.createElement('li');
        li.textContent = n;
        ul.appendChild(li);
    });
}

function renderAdjacency() {
    $('#adjacency').textContent = JSON.stringify(g.toPrettyObject(), null, 2);
}

function renderMST(res) {
    const ul = $('#mst-list');
    ul.innerHTML = '';
    res.edges.forEach(({u,v,w}) => {
        const li = document.createElement('li');
        li.textContent = `${u} — ${v} (costo ${w})`;
        ul.appendChild(li);
    });
    $('#mst-cost').textContent = `${res.cost}`;
}

function safe(action, after) {
    try {
        const r = action?.();
        after?.(r);
        renderNodes();
        renderAdjacency();
    } catch (e) {
        alert(e.message || String(e));
    }
}

window.addEventListener('DOMContentLoaded', () => {
    // agregar nodo
    $('#form-node').addEventListener('submit', (e) => {
        e.preventDefault();
        const name = $('#node-name').value.trim();
        if (!name) return;
        safe(() => g.addNode(name), () => { $('#node-name').value=''; });
    });

    // agregar arista
    $('#form-edge').addEventListener('submit', (e) => {
        e.preventDefault();
        const u = $('#edge-u').value.trim();
        const v = $('#edge-v').value.trim();
        const w = $('#edge-w').value.trim();
        if (!u || !v || !w) return;
        safe(() => g.addEdge(u, v, w));
    });

    // prim
    $('#form-prim').addEventListener('submit', (e) => {
        e.preventDefault();
        const start = $('#start-node').value.trim();
        safe(() => primMST(g, start), (res) => renderMST(res));
    });

    renderNodes();
    renderAdjacency();
});



