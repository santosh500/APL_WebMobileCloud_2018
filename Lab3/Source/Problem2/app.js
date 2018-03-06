var http=require('http');
var twitterHelp = require('./twitterHelper');

function onRequest(request, response){
    response.writeHead(200, {'Content-Type': 'text/html'});
    var q = twitterHelp.myArray;
    for(i=0; i<15; i++)
    {
        response.write(q[i].screen_name + '\n');
    }

    response.end();
}

http.createServer(onRequest).listen(8080);