<?php
function getConnection(){
    $PARAM_hote='localhost';
    $PARAM_port='5432';
    $PARAM_nom_bd='td'; 
    $PARAM_utilisateur='postgres'; 
    $PARAM_mot_passe='root'; 
        try
        {
        $connexion = new PDO('pgsql:host='.$PARAM_hote.';dbname='.$PARAM_nom_bd, $PARAM_utilisateur, $PARAM_mot_passe);
        return $connexion;
        }
        catch(Exception $e)
        {
            echo $e->getMessage();
            echo 'Une erreur est survenue !';
        die();
        }
    }
?>