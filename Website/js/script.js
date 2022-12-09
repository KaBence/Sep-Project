/*$.get("xml/person.xml", function (xml, status) {
    var firstname = $(xml).find("firstname");
    var displayName = $(firstname[0]).text();
    $("#textField1").html(displayName);
});
*/

$.get("xml/BoardgamesAvl.xml",function(xml,status){
    var txt="<table class='boardGames'><tr><th>Name</th><th>Type</th><th>Minimum Number of Players</th><th>Maximum Number of Players</th><th>Owner</th></tr>";
    $(xml).find("boardGames").each(function(){
        txt+="<tr><td>"+$(this).find("name").text()+"</td><td>"+$(this).find("type").text()+"</td><td>"+$(this).find("minNoP").text()+"</td><td>"+$(this).find("maxNoP").text()+"</td><td>"+$(this).find("owner").find("firstName").text()+" "+$(this).find("owner").find("lastName").text()+"</td></tr>";
    })
    txt+="</table>";
    $("#TableAvl").html(txt);
})

$.get("xml/BoardgamesNonAvl.xml",function(xml,status){
    var txt="<table class='boardGames'><tr><th>Name</th><th>Type</th><th>Minimum Number of Players</th><th>Maximum Number of Players</th><th>Vote</th></tr>";
    $(xml).find("boardGames").each(function(){
        txt+="<tr><td>"+$(this).find("name").text()+"</td><td>"+$(this).find("type").text()+"</td><td>"+$(this).find("minNoP").text()+"</td><td>"+$(this).find("maxNoP").text()+"</td><td>"+$(this).find("voteList").find("vote").text()+"</td></tr>";
    })
    txt+="</table>";
    $("#TableNonAvl").html(txt);
})

$.get("xml/Events.xml",function(xml,status){
    var txt="<table class='Events'><tr><th>Name</th><th>Location</th><th>Capacity</th><th>Date</th><th>BoardGames</th><th>Who is coming?</th></tr>";
    $(xml).find("events").each(function(){
        txt+="<tr><td>"+$(this).find("eventName").text()+"</td><td>"+$(this).find("location").text()+"</td><td>"+$(this).find("capacity").text()+"</td><td>"+$(this).find("date").find("day").text()+"/"+$(this).find("date").find("month").text()+"/"+$(this).find("date").find("year").text()+" "+$(this).find("date").find("hour").text()+":"+$(this).find("date").find("min").text()+"</td><td>";
        $(this).find("boardGames").each(function(){
            txt+=$(this).find("name").text()+"<br>";
        })
        txt+="</td><td>"
        $(this).find("members").each(function(){
            txt+=$(this).find("firstName").text()+" "+$(this).find("lastName").text()+"<br>";
        })
        $(this).find("guests").each(function(){
            txt+=$(this).text()+"<br>"
        })+"</td></tr>"
    })
    txt+="</table>";
    $("#tableEvents").html(txt);
})





