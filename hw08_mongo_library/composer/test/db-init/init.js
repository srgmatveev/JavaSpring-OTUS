db = db.getSiblingDB('test_library')

db.createUser({
  user: "sergio",
  pwd: "123456",
  roles: [ { role: "readWrite", db: "test_library" } ]
})

