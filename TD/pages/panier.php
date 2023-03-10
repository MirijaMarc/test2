<?php
require_once('../inc/connexion.php');
include ('../inc/fonctions.php');
session_start();
$connexion= getConnection();
$tabId = $_SESSION['panier'];
$countTabId = countQte($tabId);
$tabId = TabNoRepeat($tabId);

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../assets/styles/styleP.css">
    <link rel="stylesheet" href="../assets/styles/template/assets/css/style.css">
</head>
<body>
    <div class="content_Panier">
        <div class="content_Panier_titre">
            <span>Votre Panier</span>
            <span><a href="../inc/traitement.php?deleteAll=true">Vider le panier</a></span>
            <span><a href="accueil.php">Accueil</a></span>
            
        </div>
        <div class="content_panier_liste">
        <div class="container">
                <div class="box">
                    <div class="stat">
                        <table border=1>
                            <tr>
                                <th>Numero</th>
                                <th>Nom Produit</th>
                                <th>Quantite</th>
                                <th>Prix Unitaire</th>
                            </tr>
                            <?php
                                $i=0;
                                foreach ($tabId as $key) {
                                    $produit=getOneProduit($connexion,$key); ?>
                                    <tr>
                                        <td><?php echo $i; ?></td>
                                        <td><?php echo $produit['nom']; ?></td>
                                        <td><?php echo $countTabId[$i]; ?></td>
                                        <td><?php echo $produit['prixUnitaire']*$countTabId[$i]; ?> Ariary</td>
                                        <td><a href="../inc/traitement.php?idSupPanier=<?php echo $key;?>">Supprimer</a></td>
                                    </tr>
                                <?php $i++;
                                }
                            ?>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
    
</body>
</html>