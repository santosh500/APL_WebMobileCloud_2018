/**
 * Created by karthik on 7/14/17.
 */
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var bodyParser = require("body-parser");
var express = require('express');
var cors = require('cors');
var app = express();

var url='mongodb://root:tampa@ds123752.mlab.com:23752/librarydatabase';//1.Modify this url with the credentials of your db name and password.
var ObjectID = require('mongodb').ObjectID;

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.post('/create', function (req, res) {
    MongoClient.connect(url, function(err, db) {
        if(err)
        {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }
        insertDocument(db, req.body, function() {
            res.write("Successfully inserted");
            res.end();
        });
    });
});

app.get('/get', function (req, res) {
    MongoClient.connect(url, function(err, db) {
        if(err)
        {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }

        db.collection('books').find().toArray(function(err, result){
            if(err)
            {
                res.write("get Failed");
                res.end();
            }else
            {

                res.send(JSON.stringify(result));
            }
            console.log("Got All Documents");

        });
    });

});

app.get('/delete/:toBeDeleted_id', function (req, res) {
    // 2.Connect to MongoDB . Handle the error and write the logic for deleting the desired book
    console.log(req.url);
    var k = req.url;
    var id2 = k.split("/delete/");
    console.log(id2);
    var o = parseInt(id2[1]);
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbase = db.db("librarydatabase");
        console.log(id2[1]);
        var myquery = { "_id": ObjectID(id2[1]) };
        dbase.collection("books").deleteOne(myquery, function(err, obj) {
            if (err) throw err;
            console.log(obj.result.n + " document(s) deleted");
            db.close();
        });
    });
});


app.get('/update/:toBeUpdated_id', function (req, res) {

    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbase = db.db("librarydatabase");
        var myquery = { "_id": ObjectID(req.query._id) };
        var newvalues = {$set: {bookName: req.query.bookName, authorName: req.query.authorName, ISBN: req.query.ISBN} };
        var myoptions = { multi: true };
        dbase.collection("books").updateMany(myquery, newvalues, myoptions, function(err, res) {
            if (err) throw err;
            console.log(res.result.nModified + " record(s) updated");
            db.close();
        });
    });
    console.log(req.query._id);
    console.log(req.query.authorName);

});


var insertDocument = function(db, data, callback) {
    db.collection('books').insertOne( data, function(err, result) {
        if(err)
        {
            res.write("Registration Failed, Error While Registering");
            res.end();
        }
        console.log("Inserted a document into the books collection.");
        callback();
    });
};

var server = app.listen(8081, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log("Example app listening at http://%s:%s", host, port)
});