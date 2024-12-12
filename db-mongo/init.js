// Script de inicialização do MongoDB
db = db.getSiblingDB("meubanco"); // Substitua "meubanco" pelo nome do banco desejado

// Criar coleção "usuarios" e inserir um documento inicial
db.usuarios.insertMany([
  {
    nome: "Admin",
    email: "admin@example.com",
    senha: "senha123"
  },
  {
    nome: "User",
    email: "user@example.com",
    senha: "senha456"
  }
]);

print("Banco de dados e coleções inicializadas com sucesso!");