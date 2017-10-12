var http = require("http");
var url = require("url");
var fs = require("fs");
var path= require("path");
 
http.createServer(function(request, response){
    response.writeHead(200, {"Content-Type":"text/plain"});
    var params = url.parse(request.url,true).query;
    var filePath = path.join(__dirname, 'message.txt');
     
    console.log(params);

    var a = params.number1;
    var b = params.number2;
    var c = "Comment;"+"	"+"enter comment 1;"+"	"+a+";	"+"enter comment 2;"+";	"+b+";	" 
    var d = params.SignatureDetails;
    var s = params.SignatureValue;
    
    fs.writeFile('message.txt', c, function (err) {

        if (err) throw err;

        console.log('Got comments');

    });
	
    fs.writeFile('SignDetails.txt', d, function (err) {

        if (err) throw err;

        console.log('got Sign Details');

    });
	fs.writeFile('SignValue.txt', s, function (err) {

        if (err) throw err;

        console.log('Got Sign value');

    });
    //response.write("ok!entered");
    //response.end();
}).listen(10001);