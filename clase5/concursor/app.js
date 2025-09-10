function parseNumberArray(input){
    if(typeof input !== 'string') return [];
    return input
        .split(/[;,\s]+/)
        .filter(Boolean)
        .map(v => Number(v))
        .filter(v => Number.isFinite(v) && v >= 0)
}

function knapSack01(costs, benefits, budget){
    const n = Math.min(costs.length, benefits.length)
    const W = Math.max(0, Math.floor(budget))
    if(n === 0 || W === 0) return { maxBenefit: 0, selectedIndexes: [] }

    const dp = Array.from({ length: n + 1 }, () => Array(W + 1).fill(0))

    for(let i = 1; i <= n; i++){
        const cost = Math.floor(costs[i-1])
        const value = benefits[i-1]
        for(let w = 0; w <= W; w++){
            dp[i][w] = dp[i-1][w]
            if(cost <= w){
                const candidate = dp[i-1][w - cost] + value
                if(candidate > dp[i][w]) dp[i][w] = candidate
            }
        }
    }

    // Reconstrucción de la solución
    let w = W
    const selected = []
    for(let i = n; i >= 1; i--){
        if(dp[i][w] !== dp[i-1][w]){
            selected.push(i-1)
            w -= Math.floor(costs[i-1])
        }
    }
    selected.reverse()

    return { maxBenefit: dp[n][W], selectedIndexes: selected }
}

function formatCurrency(n){
    return new Intl.NumberFormat('es-AR', { style: 'currency', currency: 'ARS', maximumFractionDigits: 0 }).format(n)
}

function onLoad(){
    const form = document.getElementById('project-form')
    const results = document.getElementById('results')
    const summary = document.getElementById('summary')
    const details = document.getElementById('details')
    const btnExample = document.getElementById('btn-example')

    function showError(msg){
        let el = form.querySelector('.error')
        if(!el){
            el = document.createElement('div')
            el.className = 'error'
            form.appendChild(el)
        }
        el.textContent = msg
    }

    function clearError(){
        const el = form.querySelector('.error')
        if(el) el.remove()
    }

    btnExample.addEventListener('click', () => {
        document.getElementById('costs').value = '3, 4, 7, 8, 9'
        document.getElementById('benefits').value = '4, 5, 10, 11, 13'
        document.getElementById('budget').value = '17'
    })

    form.addEventListener('submit', (e) => {
        e.preventDefault()
        clearError()
        const costs = parseNumberArray(document.getElementById('costs').value)
        const benefits = parseNumberArray(document.getElementById('benefits').value)
        const budget = Number(document.getElementById('budget').value)

        if(costs.length === 0 || benefits.length === 0){
            showError('Por favor ingrese al menos un costo y un beneficio válidos.')
            return
        }
        if(costs.length !== benefits.length){
            showError('La cantidad de costos y beneficios debe coincidir.')
            return
        }
        if(!Number.isFinite(budget) || budget < 0){
            showError('Ingrese un presupuesto válido (número no negativo).')
            return
        }

        const { maxBenefit, selectedIndexes } = knapSack01(costs, benefits, budget)
        const selectedCosts = selectedIndexes.map(i => costs[i])
        const selectedBenefits = selectedIndexes.map(i => benefits[i])
        const totalCost = selectedCosts.reduce((a,b)=>a+b,0)

        results.classList.remove('hidden')
        summary.innerHTML = ''
        details.innerHTML = ''

        const elems = [
            { label: 'Presupuesto', value: formatCurrency(budget) },
            { label: 'Costo total seleccionado', value: formatCurrency(totalCost) },
            { label: 'Beneficio total', value: maxBenefit.toString() },
            { label: 'Proyectos elegidos (índices)', value: selectedIndexes.length ? selectedIndexes.join(', ') : 'Ninguno' }
        ]
        for(const {label, value} of elems){
            const div = document.createElement('div')
            div.className = 'pill'
            div.textContent = `${label}: ${value}`
            summary.appendChild(div)
        }

        const table = document.createElement('table')
        const thead = document.createElement('thead')
        thead.innerHTML = '<tr><th>#</th><th>Costo</th><th>Beneficio</th><th>Seleccionado</th></tr>'
        table.appendChild(thead)

        const tbody = document.createElement('tbody')
        for(let i=0;i<costs.length;i++){
            const tr = document.createElement('tr')
            const selected = selectedIndexes.includes(i)
            tr.innerHTML = `
                <td>${i}</td>
                <td>${formatCurrency(costs[i])}</td>
                <td>${benefits[i]}</td>
                <td>${selected ? 'Sí' : 'No'}</td>
            `
            tbody.appendChild(tr)
        }
        table.appendChild(tbody)
        details.appendChild(table)
    })
}

window.addEventListener('DOMContentLoaded', onLoad)


