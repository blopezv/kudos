#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

var url = 'amqp://niewcprb:VpUcAVvXOZf7xxBLf-aTK_XORREfUHW_@lion.rmq.cloudamqp.com/niewcprb';
var json = JSON.stringify (
  {
    "type": "NEW_KUDO",
    "model": "kudo",
    "bodyMessage": {
       "id": "5b710a0f30d06e127403fa48",
       "kudosQty": 2
    }
  }
);

amqp.connect(url, function(err, conn) {
  conn.createChannel(function(err, ch) {
    var ex = json ;
    var msg = process.argv.slice(2).join(' ') || 'New Kudo!';

    ch.assertExchange(ex, 'fanout', {durable: false});
    ch.publish(ex, '', new Buffer(msg));
    console.log(" [x] Sent %s", msg);
  });
  setTimeout(function() { conn.close(); process.exit(0) }, 500);
});




