package de.salsacompany.app.model

data class Branch(val id:String,val name:String,val address:String,val url:String,val note:String="")
data class DanceClass(val id:String,val style:String,val title:String,val level:String,val day:String,val time:String,val branchId:String,val bookingUrl:String)
data class Price(val name:String,val monthly:String,val detail:String)
data class Teacher(val name:String,val styles:String)
data class EventLink(val title:String,val subtitle:String,val url:String)

object OfficialContent {
    val branches = listOf(
        Branch("stuttgart","Stuttgart","Mercedesstraße 7 · 70372 Stuttgart","https://salsa-tanzschule-stuttgart.de/"),
        Branch("ludwigsburg","Ludwigsburg","Myliusstraße 13 · 71638 Ludwigsburg","https://salsa-company-ludwigsburg.de/"),
        Branch("boeblingen","Böblingen","Adresse vor Besuch auf Website prüfen","https://tanzschule-boeblingen.de/","Die offiziellen Seiten nennen unterschiedliche Adressen."),
        Branch("heilbronn","Heilbronn","Weststraße 28 · 74072 Heilbronn","https://salsa-tanzschule-heilbronn.de/")
    )
    val classes = listOf(
        DanceClass("s1","Salsa","Salsa","A3","Montag","19:00–20:00","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("s2","Salsa","Salsa","A2","Montag","20:15–21:15","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("s3","Salsa","Salsa","A1","Montag","21:30–22:30","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("b1","Bachata","Bachata Fusion","A2","Dienstag","20:15–21:15","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("b2","Bachata","Bachata Sensual","A1","Mittwoch","19:00–20:00","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("k1","Kizomba","Kizomba","A1","Mittwoch","19:00–20:00","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/"),
        DanceClass("z1","Zouk","Brazilian Zouk","A1","Montag","19:00–20:00","stuttgart","https://salsa-tanzschule-stuttgart.de/schnupperstunde/")
    )
    val prices = listOf(
        Price("Salsa Pass","ab 55 € / Monat","1 Tanz · Vertragslaufzeit beeinflusst den Preis"),
        Price("Bachata Pass","ab 65 € / Monat","Bachata-Kurse"),
        Price("Combi Pass","ab 80 € / Monat","2 Tänze"),
        Price("All you can dance","ab 95 € / Monat","Alle regulären Kurse"),
        Price("Probestunde","15 €","Barzahlung am Kurstag laut Stuttgart-Website")
    )
    val teachers = listOf("Kia","Alika","Anastasia","Andre","Danika","Alejandro","Carolina","Selina","Petra","Gilles","Ali","Nata","Mirella","Zoltan","Aziz").map { Teacher(it,"Salsa · Bachata · Kizomba · Zouk") }
    val events = listOf(
        EventLink("Latin Festival","Workshops & Parties","https://www.latin-festival.de/"),
        EventLink("Bachata Festival","Bachata weekend","https://bachatafestival-stuttgart.de/"),
        EventLink("Cuba Festival","Cuban dance festival","https://www.cuba-festival.com/"),
        EventLink("Salsa Marathon","Dance marathon","https://www.dance-marathon.de/")
    )
}
