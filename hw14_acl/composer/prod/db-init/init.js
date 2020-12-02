db = db.getSiblingDB('admin')

db.createUser({
  user: "sergio",
  pwd: "123456",
  roles: [ { role: "readWrite", db: "library" } ]
})

