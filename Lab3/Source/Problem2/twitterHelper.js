var Twitter = require('twitter');
var http = require('http');
var fs = require('fs');
var client = new Twitter({
    consumer_key: 'NrAYNgxAWrzadiuCUNmT0gahq',
    consumer_secret: 'hDEJvT3VSWwARsGdhHyYCRckAltAM73wzQ8Hm1Knk0vyk6RKFn',
    access_token_key: '966157340289495042-2KBP37lJKojwAaAyPkoSpUO0B5rPTTN',
    access_token_secret: 'BDlCQRaVd3H9Kd9ETDn6cHcFxG4JoeSuMCxoLFdKLHTpe'
});

var o;
var mainArray;
var params = {screen_name: 'realDonaldTrump'};
client.get('friends/list', params, function(error, tweets, response) {
    if (!error)
    {
        // console.log(tweets);
        o = tweets.toString();

        var myJSON = JSON.stringify(tweets);
        var obj = JSON.parse(myJSON);
        var tacos = obj.users;
        mainArray = tacos;
        module.exports.myArray = mainArray;
        for(i=0; i<tacos.length; i++)
        {
            console.log(tacos[i].screen_name);
            var server=http.createServer(function(req,res){
                res.writeHead(tacos[i].screen_name, {'Content-Type': 'text/html'});
                var myReadStream = fs.createReadStream(_dirname + '/index.html', 'utf8');
                myReadStream.pipe(res);
            });

        }
    }
});

