<?php
require_once('../inc/connexion.php');
include ('../inc/fonctions.php');
$connexion = getConnection();
$produits= getAllProduits($connexion);

?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../assets/styles/style.css">
</head>
<body>

    <div class="content">
        <div class="content_title">
            <span>Liste des Produits</span>
            <span><a href="panier.php"> Voir le Panier</a></span>
        </div>
        <div class="content_list">
            <div class="container">
                <div class="box">
                    <div class="stat">
                        <table border=1>
                            <tr>
                                <th>Numero Produit</th>
                                <th>Nom</th>
                                <th>Prix Unitaire</th>
                            </tr>
                            <?php
                                foreach ($produits as $key) { ?>
                                    <tr>
                                        <td><?php echo $key['idProduit']; ?></td>
                                        <td><?php echo $key['nom']; ?></td>
                                        <td><?php echo $key['prixUnitaire']; ?> Ariary</td>
                                        <td><a href="../inc/traitement.php?idProduit=<?php echo $key['idProduit']; ?>">Ajouter au Panier</a></td>
                                    </tr>
                                <?php }
                            ?>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>