<?php
require_once("helper.php");
$id = $_GET['id'];
$character     = json_decode(curl_contents("https://anapioficeandfire.com/api/characters/".$id),true);

$allegiance = "";
$books = "";

foreach($character['allegiances'] as $allegianceLink){
    $result = json_decode(curl_contents($allegianceLink),true);
    $allegiance .=$result['name']." ";
}

foreach($character['books'] as $bookLink){
    $result = json_decode(curl_contents($bookLink),true);
    $books .=$result['name']." ";
}
?>

<html>
<header>
</header>

<body>
<h4>Character Details</h4>
<table id="characters">
               
                <tbody>
                    <tr><td>Aliases</td><td><?= implode(", ",$character['aliases']) ?></td></tr>
                    <tr><td>Allegiances</td><td> <?= $allegiance; ?></td></tr>
                    <tr><td>Books</td><td> <?= $books ?></td></tr>
                    <tr><td>Born</td><td> <?= $character['born'] ?> </td></tr>
                    <tr><td>Culture</td><td><?= $character['culture'] ?></td></tr>
                    <tr><td>Died</td><td> <?= $character['died'] ?></td></tr>
                    <tr><td>Father</td><td> <?= $character['father'] ?></td></tr>
                    <tr><td>Gender</td><td> <?= $character['gender'] ?></td></tr>
                    <tr><td>Mother</td><td> <?= $character['mother'] ?></td></tr>
                    <tr><td>Name</td><td> <?= $character['name'] ?></td></tr>
                    <tr><td>Played By</td><td> <?= implode(", ",$character['playedBy']) ?></td></tr>
                    <tr><td>Titles</td><td> <?= implode(", ",$character['titles']) ?></td></tr>
                    <tr><td>Tv Series</td><td><?= implode(", ",$character['tvSeries']) ?></td></tr>
                </tbody>
            </table>
</body>

</html>