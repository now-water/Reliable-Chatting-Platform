module.exports = function () {
    var mysql = require('mysql');
    var conn = mysql.createConnection({
        host: 'database-1.cdifv5qqrjfj.ap-northeast-2.rds.amazonaws.com',
        user: 'asdasd',
        password: 'asdasd',
        database: 'asdasd',
    });
    conn.connect();
    console.log("DB Conntected");
    return conn;
}