var express = require('express');
var bodyParser = require('body-parser');

var app = express();
var conn = require('./config/db')();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(__dirname));

app.get('/msg/test', (req, res) => {
    res.status(200).send('asdasd');
})

app.listen(3000, function () {
    console.log('Connected 3000 ! ')
});

