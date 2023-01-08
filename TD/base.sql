create database td;

\c td;

create table Produit(
    idProduit varchar(10) primary key,
    nom varchar(20),
    prixUnitaire decimal
);

insert into Produit values('1','short',12000);
insert into Produit values('2','tee-shirt',17500);
insert into Produit values('3','jupe',2000);
insert into Produit values('4','robe',34000);
insert into Produit values('5','doudoune',18500);
insert into Produit values('6','survetement',13000);
insert into Produit values('7','gants',10000);
insert into Produit values('8','chaussettes',7000);
insert into Produit values('9','debardeur',5500);
insert into Produit values('10','pantalon',13000);