class DirectedGraph {
    constructor() {
        // Lista de adyacencia de salientes: usuario -> Set de a quién sigue
        this.outgoing = new Map();
        // Para consultas eficientes de seguidores, mantenemos también entrantes: usuario -> Set de quiénes lo siguen
        this.incoming = new Map();
    }

    ensureUser(user) {
        const u = user.trim();
        if (!u) throw new Error("Usuario inválido");
        if (!this.outgoing.has(u)) this.outgoing.set(u, new Set());
        if (!this.incoming.has(u)) this.incoming.set(u, new Set());
        return u;
    }

    addUser(user) {
        this.ensureUser(user);
        return true;
    }

    follow(follower, followee) {
        const a = this.ensureUser(follower);
        const b = this.ensureUser(followee);
        if (a === b) throw new Error("Un usuario no puede seguirse a sí mismo");
        const out = this.outgoing.get(a);
        const added = !out.has(b);
        out.add(b);
        this.incoming.get(b).add(a);
        return added; // true si se agregó una relación nueva
    }

    unfollow(follower, followee) {
        const a = this.ensureUser(follower);
        const b = this.ensureUser(followee);
        const out = this.outgoing.get(a);
        const removed = out.delete(b);
        if (removed) this.incoming.get(b).delete(a);
        return removed;
    }

    getUsers() {
        const names = new Set([...this.outgoing.keys(), ...this.incoming.keys()]);
        return [...names].sort();
    }

    getFollowing(user) {
        const u = this.ensureUser(user);
        return [...this.outgoing.get(u)].sort();
    }

    getFollowers(user) {
        const u = this.ensureUser(user);
        return [...this.incoming.get(u)].sort();
    }

    getMutuals(user) {
        const following = new Set(this.getFollowing(user));
        const followers = new Set(this.getFollowers(user));
        return [...following].filter(x => followers.has(x)).sort();
    }

    toPrettyObject() {
        const users = this.getUsers();
        const out = {};
        const inc = {};
        users.forEach(u => {
            out[u] = [...(this.outgoing.get(u) || new Set())].sort();
            inc[u] = [...(this.incoming.get(u) || new Set())].sort();
        });
        return { outgoing: out, incoming: inc };
    }
}

// --- Estado de la app ---
const graph = new DirectedGraph();

// Semillas opcionales para probar rápidamente
;['ana', 'bob', 'cami', 'diego'].forEach(u => graph.addUser(u));
graph.follow('ana', 'bob');
graph.follow('bob', 'cami');
graph.follow('cami', 'ana');
graph.follow('ana', 'cami');

// --- Utilidades de UI ---
const $ = (sel) => document.querySelector(sel);
const $$ = (sel) => document.querySelectorAll(sel);

function renderUsers() {
    const ul = $('#users-list');
    ul.innerHTML = '';
    graph.getUsers().forEach(u => {
        const li = document.createElement('li');
        li.textContent = u;
        ul.appendChild(li);
    });
}

function renderAdjacency() {
    const { outgoing, incoming } = graph.toPrettyObject();
    $('#adj-out').textContent = JSON.stringify(outgoing, null, 2);
    $('#adj-in').textContent = JSON.stringify(incoming, null, 2);
}

function renderList(targetSelector, items) {
    const ul = $(targetSelector);
    ul.innerHTML = '';
    if (items.length === 0) {
        const li = document.createElement('li');
        li.textContent = '— vacío —';
        li.style.color = '#98a1c0';
        ul.appendChild(li);
        return;
    }
    items.forEach(x => {
        const li = document.createElement('li');
        li.textContent = x;
        ul.appendChild(li);
    });
}

function safeAction(action, onSuccess) {
    try {
        const res = action();
        onSuccess?.(res);
        renderUsers();
        renderAdjacency();
    } catch (err) {
        alert(err.message || String(err));
    }
}

// --- Eventos ---
window.addEventListener('DOMContentLoaded', () => {
    // Agregar usuario
    $('#form-add-user').addEventListener('submit', (e) => {
        e.preventDefault();
        const input = $('#user-name');
        const name = input.value.trim();
        if (!name) return;
        safeAction(() => graph.addUser(name), () => { input.value = ''; });
    });

    // Seguir / Dejar de seguir
    $('#form-follow').addEventListener('submit', (e) => {
        e.preventDefault();
        const a = $('#follower').value.trim();
        const b = $('#followee').value.trim();
        if (!a || !b) return;
        safeAction(() => graph.follow(a, b));
    });
    $('#btn-unfollow').addEventListener('click', () => {
        const a = $('#follower').value.trim();
        const b = $('#followee').value.trim();
        if (!a || !b) return;
        safeAction(() => graph.unfollow(a, b));
    });

    // Consultas
    $('#btn-following').addEventListener('click', () => {
        const u = $('#query-user').value.trim();
        if (!u) return;
        safeAction(() => null, () => renderList('#list-following', graph.getFollowing(u)));
    });
    $('#btn-followers').addEventListener('click', () => {
        const u = $('#query-user').value.trim();
        if (!u) return;
        safeAction(() => null, () => renderList('#list-followers', graph.getFollowers(u)));
    });
    $('#btn-mutuals').addEventListener('click', () => {
        const u = $('#query-user').value.trim();
        if (!u) return;
        safeAction(() => null, () => renderList('#list-mutuals', graph.getMutuals(u)));
    });

    // Primer render
    renderUsers();
    renderAdjacency();
});


