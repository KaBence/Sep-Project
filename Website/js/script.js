/*$.get("xml/person.xml", function (xml, status) {
    var firstname = $(xml).find("firstname");
    var displayName = $(firstname[0]).text();
    $("#textField1").html(displayName);
});
*/

$.get("xml/BoardgamesAvl.xml",function(xml,status){
    var txt="<table class='boardGames'><tr><th>Name</th><th>Type</th><th>Minimum Number of Players</th><th>Maximum Number of Players</th><th>Owner</th></tr>";
    $(xml).find("boardGames").each(function(){
        txt+="<tr><td>"+$(this).find("name").text()+"</td><td>"+$(this).find("type").text()+"</td><td>"+$(this).find("minNoP").text()+"</td><td>"+$(this).find("maxNoP").text()+"</td><td>"+$(this).find("owner").find("firstName").text()+" "+$(this).find("owner").find("lastName").text();
    })
    txt+="</table>";
    $("#TableAvl").html(txt);
})

$.get("xml/BoardgamesNonAvl.xml",function(xml,status){
    var txt="<table class='boardGames'><tr><th>Name</th><th>Type</th><th>Minimum Number of Players</th><th>Maximum Number of Players</th><th>Vote</th></tr>";
    $(xml).find("boardGames").each(function(){
        txt+="<tr><td>"+$(this).find("name").text()+"</td><td>"+$(this).find("type").text()+"</td><td>"+$(this).find("minNoP").text()+"</td><td>"+$(this).find("maxNoP").text()+"</td><td>"+$(this).find("voteList").find("vote").text();
    })
    txt+="</table>";
    $("#TableNonAvl").html(txt);
})





