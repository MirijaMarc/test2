<?php
require_once('connexion.php');


function getOneProduit($connexion,$id){
    $produit=array();
    $req= sprintf("SELECT * FROM Produit Where idproduit='%s'",$id);
     $result = $connexion->query($req);
     $result->setFetchMode(PDO::FETCH_OBJ);
     while ($ligne = $result->fetch()) {
        $produit['idProduit']=$ligne->idproduit;
        $produit['nom']=$ligne->nom;
        $produit['prixUnitaire']=$ligne->prixunitaire;
     }
     $result->closeCursor();
     return $produit;
}

function getAllProduits($connexion){
    $produit=array();
    $req= sprintf("SELECT * FROM Produit ");
     $result = $connexion->query($req);
     $result->setFetchMode(PDO::FETCH_OBJ);
     $i=0;
     while ($ligne = $result->fetch()) {
        $produit[$i]['idProduit']=$ligne->idproduit;
        $produit[$i]['nom']=$ligne->nom;
        $produit[$i]['prixUnitaire']=$ligne->prixunitaire;
        $i++;
     }
     $result->closeCursor();
     return $produit;
}

function TabNoRepeat($tableau){
   $rep = array_unique($tableau);
   return $rep;
}

function countQte($tableau){
   $counts = array();
   $tabNoRepeat = TabNoRepeat($tableau);
   foreach ($tabNoRepeat as $element){
      $counts[]=count(array_keys($tableau, $element));
   }
   return $counts;
}



?>