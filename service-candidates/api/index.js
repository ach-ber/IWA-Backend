const express = require('express')
const app = express()
const PORT = 3000
const HOST = "0.0.0.0"

app.get("/candidates", (req, res) => {
    res.sendFile(__dirname + "/fake_candidates.json")
})

app.listen(PORT, HOST, () => {
    console.log(`Candidates API available on http://localhost:${PORT}/candidates`)
})
