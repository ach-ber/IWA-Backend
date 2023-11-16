const express = require('express')
const app = express()
const PORT = 8303
const HOST = "0.0.0.0"

app.get("/candidates", (req, res) => {
    res.sendFile(__dirname + "/fake_candidates.json")
})

app.get("/candidates/:id", (req, res) => {
    const candidates = require("./fake_candidates.json").candidates
    const candidate = candidates.find(c => c.id == req.params.id)
    if (candidate) {
        res.json(candidate)
    } else {
        res.status(404).send("Not found")
    }
})

app.listen(PORT, HOST, () => {
    console.log(`Candidates API available on http://localhost:${PORT}/candidates`)
})
