const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());
app.get('/health', (req, res) => res.status(200).send('Express Service'));

//router

const UserRouter = require('./routers/user.router');

app.use('/api/users', UserRouter);

module.exports = app;