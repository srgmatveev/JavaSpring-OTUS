db = db.getSiblingDB('admin')

db.createUser({
  user: "andrea",
  pwd: "123456789",
  roles: [ { role: "readWrite", db: "library" } ]
})

db.library.insert({
  name: "Steve Jobs"
})