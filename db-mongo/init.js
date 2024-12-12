// Script de inicialização do MongoDB
db = db.getSiblingDB("db_dac"); // Substitua "meubanco" pelo nome do banco desejado

// Criar coleção "usuarios" e inserir um documento inicial
db.usuarios.insertMany([
  {
    nome: "Admin",
    email: "admin@example.com",
    senha: "senha123",
    type: 2
  },
  {
    nome: "User",
    email: "user@example.com",
    senha: "senha456",
    type: 1
  }
]);

print("Banco de dados e coleções inicializadas com sucesso!");