// -- 1.

db.restaurants.find();


// -- 2.

db.restaurants.find({ }, {_id:1, name:1, restaurant_id:1, borough:1, cuisine:1}).pretty();

// -- 3.

db.restaurants.find({ }, {_id:0, name:1, restaurant_id:1, borough:1, cuisine:1}).pretty();

// -- 4.

db.restaurants.find({ }, {_id:1, name:1, restaurant_id:1, borough:1, "address.zipcode":1}).pretty();

// -- 5.

db.restaurants.find({borough:"Bronx"}).pretty();

// -- 6.

db.restaurants.find({borough:"Bronx"}).limit(5).pretty();

// -- 7.

db.restaurants.find({borough:"Bronx"}).skip(5).limit(5).pretty();

// -- 8.

db.restaurants.find({"grades.score": {$gt: 90}}, {name:1 });

// --9.

db.restaurants.find({"grades.score": {$gt: 90, $lt: 100}, {name:1 });


