<?php
session_start();
if (isset($_GET['idProduit'])){
    $tableau=$_SESSION['panier'];
    $tableau[]=$_GET['idProduit'];
    $_SESSION['panier']=$tableau;
    header('Location:../pages/accueil.php');
}

if (isset($_GET['idSupPanier'])){
    $tableau=$_SESSION['panier'];
    $tableau = array_merge(array_diff($tableau,array($_GET['idSupPanier'])));
    $_SESSION['panier']=$tableau;
    header('Location:../pages/panier.php');
}

if (isset($_GET['deleteAll'])){
    $_SESSION['panier']= array();
    header('Location:../pages/panier.php');

}


?>