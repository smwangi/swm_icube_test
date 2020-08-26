<?php
require_once("helper.php");
$books          = json_decode(curl_contents("https://anapioficeandfire.com/api/books"),true);
$characters     = json_decode(curl_contents("https://anapioficeandfire.com/api/characters"),true);
$houses         = json_decode(curl_contents("https://anapioficeandfire.com/api/houses"),true);
?>

<html>
<header>
    <style>
    .flex-container {
        display: flex;
    }

    .flex-child {
        flex: 1;
        border: 1px solid grey;
        overflow-x: auto;
        overflow-y: auto;
    }

    .flex-child:first-child {
        margin-right: 20px;
        overflow-x: auto;
        overflow-y: auto;
    }
    </style>
</header>

<body>
    <h4>Books</h4>
    <div class="flex-container">

        <div class="flex-child magenta">
            <table id="books">
                <thead>
                    <tr>
                        <th>
                            Name
                        </th>
                        <th>
                            ISBN
                        </th>
                        <th>
                            Publisher
                        </th>
                        <th>
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach($books as $book): ?>
                    <tr>
                        <td><?= $book['name'] ?></td>
                        <td><?= $book['isbn'] ?></td>
                        <td><?= $book['publisher'] ?></td>
                        <td><Button class="book-details" data-url="<?= $book['url']?>">Click to view...</Button></td>
                    </tr>
                    <?php endforeach ?>
                </tbody>
            </table>
        </div>

        <div class="flex-child green">
            <div id="details"></div>
        </div>

    </div>

    <hr>
    <h4>Characters</h4>
    <div class="flex-container">


        <div class="flex-child magenta">
            <table id="characters">
                <thead>
                    <tr>
                        <th>
                            Name
                        </th>
                        <th>
                            Culture
                        </th>
                        <th>
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach($characters as $character): ?>
                    <tr>
                        <td><?= $character['name'] ?></td>
                        <td><?= $character['culture'] ?></td>
                        <td><Button class="character-details" data-url="<?= $character['url']?>">Click to
                                view...</Button></td>
                    </tr>
                    <?php endforeach ?>
                </tbody>
            </table>
        </div>
        <div class="flex-child green">
            <div id="character-details"></div>
        </div>
    </div>
    <hr>
    <h4>Houses</h4>
    <div class="flex-container">

        <div class="flex-child magenta">
            <table id="houses">
                <thead>
                    <tr>
                        <th>
                            Name
                        </th>
                        <th>
                            Culture
                        </th>
                        <th>
                            Action
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach($houses as $house): ?>
                    <tr>
                        <td><?= $house['name'] ?></td>
                        <td><?= $house['region'] ?></td>
                        <td><Button class="house-details" data-url="<?= $house['url']?>">Click to view...</Button></td>
                    </tr>
                    <?php endforeach ?>
                </tbody>
            </table>
        </div>
        <div class="flex-child green">
            <div id="house-details"></div>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript">
$('.book-details').on('click', function() {

    let url = $(this).data('url');

    callAPI(url).done(function(data) {
        $('#details').empty();
        let html = '<table>';

        $.each(data.characters, function(k, v) {

            let id = v.substring(v.lastIndexOf("/") + 1);
            callAPI(v).done(function(character) {

                $('.character').append('<a href="character_details.php?id=' + id +
                    '" target="_blank">' + character.name + '</>').append(", ");
            });
        });

        $.each(data.povCharacters, function(k, v) {

            let id = v.substring(v.lastIndexOf("/") + 1);
            callAPI(v).done(function(povCharacter) {

                $('.povCharacter').append('<a href="character_details.php?id=' + id +
                    '" target="_blank">' + povCharacter.name + '</>').append(", ");
            });
        });
        console.log(data);
        html += '<tr><td>Book</td><td>' + data.name + '</td></tr>';
        html += '<tr><td>Authors</td><td>' + data.authors.toString() + '</td></tr>';
        html += '<tr><td>Country</td><td>' + data.country + '</td></tr>';
        html += '<tr><td>ISBN</td><td>' + data.isbn + '</td></tr>';
        html += '<tr><td>Media Type</td><td>' + data.mediaType + '</td></tr>';
        html += '<tr><td>No. of Pages</td><td>' + data.numberOfPages + '</td></tr>';
        html += '<tr><td>Publisher</td><td>' + data.publisher + '</td></tr>';
        html += '<tr><td>Released</td><td>' + data.released + '</td></tr>';
        html += '<tr><td>Character</td><td class="character"></td></tr>';//' + data.released + '
        html += '<tr><td>Pov Character</td><td class="povCharacter"></td></tr>';//' + data.released + '
        html += '</table>';

        $('#details').html(html);
    });

});

$('.character-details').on('click', function() {

    let url = $(this).data('url');

    callAPI(url).done(function(data) {
        $('#character-details').empty();
        let html = '<table>';

        console.log(data);
        html += '<tr><td>Aliases</td><td>' + data.aliases.toString() + '</td></tr>';
        html += '<tr><td>Allegiances</td><td>' + data.allegiances.toString() + '</td></tr>';
        html += '<tr><td>Books</td><td>' + data.books.toString() + '</td></tr>';
        html += '<tr><td>Born</td><td>' + data.born + '</td></tr>';
        html += '<tr><td>Culture</td><td>' + data.culture + '</td></tr>';
        html += '<tr><td>Died</td><td>' + data.died + '</td></tr>';
        html += '<tr><td>Father</td><td>' + data.father + '</td></tr>';
        html += '<tr><td>Gender</td><td>' + data.gender + '</td></tr>';
        html += '<tr><td>Mother</td><td>' + data.mother + '</td></tr>';
        html += '<tr><td>Name</td><td>' + data.name + '</td></tr>';
        html += '<tr><td>Played By</td><td>' + data.playedBy.toString() + '</td></tr>';
        html += '<tr><td>Gender</td><td>' + data.povBooks.toString() + '</td></tr>';
        html += '<tr><td>Titles</td><td>' + data.titles.toString() + '</td></tr>';
        html += '<tr><td>Tv Series</td><td>' + data.tvSeries.toString() + '</td></tr>';
        html += '</table>';

        $('#character-details').html(html);
    });
});

$('.house-details').on('click', function() {

    let url = $(this).data('url');

    callAPI(url).done(function(data) {
        $('#house-details').empty();
        let html = '<table>';

        callAPI(data.overlord).done(function(overlord) {

            $('.overlord').append(overlord.name);
        });

        if (data.currentLord !== "")
            callAPI(data.currentLord).done(function(currentLord) {
                let id = data.currentLord.substring(data.currentLord.lastIndexOf("/") + 1);
                $('.currentLord').append('<a href="character_details.php?id=' + id +
                    '" target="_blank">' + currentLord.name + '</>');
            });

        if (data.heir !== "")
            callAPI(data.heir).done(function(heir) {
                let id = data.heir.substring(data.heir.lastIndexOf("/") + 1);
                $('.heir').append('<a href="character_details.php?id=' + id +
                    '" target="_blank">' + heir.name + '</>');
            });

        $.each(data.swornMembers, function(k, v) {

            let id = v.substring(v.lastIndexOf("/") + 1);
            callAPI(v).done(function(swornmember) {

                $('.swornmember').append('<a href="character_details.php?id=' + id +
                    '" target="_blank">' + swornmember.name + '</>').append(", ");
            });
        });

        html += '<tr><td>Ancestral Weapons</td><td>' + data.ancestralWeapons.toString() + '</td></tr>';
        html += '<tr><td>Cadet Branches</td><td>' + data.cadetBranches + '</td></tr>';
        html += '<tr><td>Coat of Arms</td><td>' + data.coatOfArms + '</td></tr>';
        html +=
            '<tr><td>Current Lord</td><td class="currentLord"></td></tr>'; //' + data.currentLord + '
        html += '<tr><td>Died Out</td><td>' + data.diedOut + '</td></tr>';
        html += '<tr><td>Founded</td><td>' + data.founded + '</td></tr>';
        html += '<tr><td>Founder</td><td>' + data.founder + '</td></tr>';
        html += '<tr><td>Heir</td><td class="heir"></td></tr>'; //' + data.heir + '
        html += '<tr><td>Name</td><td>' + data.name + '</td></tr>';
        html += '<tr><td>Overlord</td><td  class="overlord"></td></tr>'; //' +data.overlord+ '    
        html += '<tr><td>Region</td><td>' + data.region + '</td></tr>';
        html += '<tr><td>Seats</td><td>' + data.seats.toString() + '</td></tr>';
        html +=
            '<tr><td>Sworn Members</td><td class="swornmember"></td></tr>'; //' + data.swornMembers.toString() + '
        html += '<tr><td>Titles</td><td>' + data.titles.toString() + '</td></tr>';
        html += '<tr><td>Words</td><td>' + data.words + '</td></tr>';
        html += '</table>';

        $('#house-details').html(html);
    });

});

function callAPI(url) {
    return $.get(url, 'json');
}
</script>

</html>