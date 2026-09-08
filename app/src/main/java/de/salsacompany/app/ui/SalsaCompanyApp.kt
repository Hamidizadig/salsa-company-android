package de.salsacompany.app.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.salsacompany.app.data.BundledContentRepository
import de.salsacompany.app.model.*

private val Ink=Color(0xFF171214); private val Coral=Color(0xFFC93B4A); private val Gold=Color(0xFFF2B84B); private val Ivory=Color(0xFFFFF8F3)
private enum class Tab(val title:String){ HOME("Start"), COURSES("Kurse"), EVENTS("Events"), MORE("Mehr") }

@Composable fun SalsaCompanyApp(){
    val repo=remember{BundledContentRepository()}; var tab by rememberSaveable{mutableStateOf(Tab.HOME)}
    var branchId by rememberSaveable{mutableStateOf("stuttgart")}; var showBranches by remember{mutableStateOf(false)}
    MaterialTheme(colorScheme=lightColorScheme(primary=Coral,secondary=Gold,background=Ivory,surface=Color.White,onPrimary=Color.White,onBackground=Ink), typography=Typography()){
        Scaffold(containerColor=Ivory,topBar={Header(repo.branches().first{it.id==branchId}){showBranches=true}},bottomBar={NavigationBar(containerColor=Ink){
            listOf(Tab.HOME to Icons.Outlined.Home,Tab.COURSES to Icons.Outlined.CalendarMonth,Tab.EVENTS to Icons.Outlined.Celebration,Tab.MORE to Icons.Outlined.Menu).forEach{(item,icon)->
                NavigationBarItem(selected=tab==item,onClick={tab=item},icon={Icon(icon,null)},label={Text(item.title)},colors=NavigationBarItemDefaults.colors(selectedIconColor=Gold,selectedTextColor=Gold,unselectedIconColor=Color.White.copy(.7f),unselectedTextColor=Color.White.copy(.7f),indicatorColor=Color.Transparent))
            }} }){ pad-> Box(Modifier.padding(pad).fillMaxSize()){
                when(tab){Tab.HOME->Home(repo,branchId,{tab=Tab.COURSES});Tab.COURSES->Courses(repo,branchId);Tab.EVENTS->Events(repo);Tab.MORE->More(repo,branchId)}
            }}
        if(showBranches) ModalBottomSheet(onDismissRequest={showBranches=false}){repo.branches().forEach{b->ListItem(headlineContent={Text(b.name,fontWeight=FontWeight.Bold)},supportingContent={Text(b.address)},leadingContent={Icon(Icons.Outlined.LocationOn,null)},modifier=Modifier.clickable{branchId=b.id;showBranches=false})};Spacer(Modifier.height(24.dp))}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable private fun Header(branch:Branch,onBranch:()->Unit)=TopAppBar(title={Column{Text("SALSA COMPANY",fontWeight=FontWeight.Black,letterSpacing=1.sp);Text(branch.name,fontSize=12.sp,color=Gold)}},actions={TextButton(onClick=onBranch){Icon(Icons.Outlined.LocationOn,null,tint=Gold);Text(" Standort",color=Color.White)}},colors=TopAppBarDefaults.topAppBarColors(containerColor=Ink,titleContentColor=Color.White))

@Composable private fun Home(repo:BundledContentRepository,branch:String,onCourses:()->Unit)=LazyColumn(contentPadding=PaddingValues(20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){
    item{Box(Modifier.fillMaxWidth().background(Ink,RoundedCornerShape(24.dp)).padding(24.dp)){Column{Text("DANCE LIKE EVERYONE’S WATCHING",color=Gold,fontWeight=FontWeight.Black,fontSize=25.sp,lineHeight=29.sp,modifier=Modifier.semantics{heading()});Spacer(Modifier.height(8.dp));Text("Salsa · Bachata · Kizomba · Zouk",color=Color.White);Spacer(Modifier.height(20.dp));Button(onClick=onCourses){Text("Stundenplan ansehen")}}}}
    item{Title("Heute & diese Woche"); val cs=repo.classes(branch); if(cs.isEmpty()) Empty("Der Stundenplan dieser Filiale wird derzeit über die offizielle Website gepflegt.") else cs.take(3).forEach{ClassCard(it)}}
    item{Notice("Aktuelle Ferien, Änderungen und Sondertermine bitte vor der Anfahrt auf der Filial-Website prüfen.")}
    item{Title("Tanzen lernen – auch ohne Partner");Text("Die Kurse sind für Einzelpersonen und Paare geeignet. In den Kursen wird regelmäßig gewechselt.",color=Ink.copy(.75f))}
}

@Composable private fun Courses(repo:BundledContentRepository,branch:String){var style by rememberSaveable{mutableStateOf("Alle")};val all=repo.classes(branch);val shown=if(style=="Alle")all else all.filter{it.style==style};LazyColumn(contentPadding=PaddingValues(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){item{Title("Stundenplan");ScrollChips(listOf("Alle","Salsa","Bachata","Kizomba","Zouk"),style){style=it}};if(shown.isEmpty())item{Empty("Für diese Auswahl liegen noch keine bestätigten App-Daten vor. Öffne die Filial-Website unter „Mehr“.")}else items(shown,key={it.id}){ClassCard(it)}}}

@Composable private fun Events(repo:BundledContentRepository)=LazyColumn(contentPadding=PaddingValues(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){item{Title("Festivals & Events")};items(repo.events()){e->LinkCard(e.title,e.subtitle,e.url,Icons.Outlined.Celebration)}}

@Composable private fun More(repo:BundledContentRepository,branchId:String){val branch=repo.branches().first{it.id==branchId};LazyColumn(contentPadding=PaddingValues(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){item{Title("Entdecken")};item{LinkCard("Preise","Pässe und Konditionen",if(branchId=="stuttgart")"https://salsa-tanzschule-stuttgart.de/preise/" else branch.url,Icons.Outlined.Payments)};item{LinkCard("Probestunde","Unverbindlich ausprobieren",if(branchId=="stuttgart")"https://salsa-tanzschule-stuttgart.de/schnupperstunde/" else branch.url,Icons.Outlined.Star)};item{LinkCard("Lernvideos","Externer Mitgliederbereich","https://tutorial-sc.de/",Icons.Outlined.PlayCircle)};item{LinkCard("Tanzlehrer","Unser Team","https://www.salsa-company.de/",Icons.Outlined.Groups)};item{LinkCard("Filiale & Kontakt",branch.address,branch.url,Icons.Outlined.Map)};item{LinkCard("Datenschutz & Impressum","Rechtliche Informationen","https://www.salsa-company.de/datenschutzerklaerung/",Icons.Outlined.Policy)};item{Notice("Datenstand: Website-Momentaufnahme. Zeitpläne, Preise und Adressen vor verbindlichen Entscheidungen auf der offiziellen Seite bestätigen.")}}}

@Composable private fun ClassCard(c:DanceClass){val context=LocalContext.current;ElevatedCard(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp)){Row(verticalAlignment=Alignment.CenterVertically){AssistChip(onClick={},label={Text(c.style)});Spacer(Modifier.width(8.dp));AssistChip(onClick={},label={Text(c.level)})};Text(c.title,fontWeight=FontWeight.Bold,fontSize=19.sp);Text("${c.day} · ${c.time}",color=Ink.copy(.7f));Spacer(Modifier.height(10.dp));Button(onClick={open(context,c.bookingUrl)},modifier=Modifier.fillMaxWidth()){Text("Probestunde anfragen")}}}}
@Composable private fun LinkCard(title:String,subtitle:String,url:String,icon:androidx.compose.ui.graphics.vector.ImageVector){val c=LocalContext.current;ElevatedCard(Modifier.fillMaxWidth().clickable{open(c,url)}){ListItem(headlineContent={Text(title,fontWeight=FontWeight.Bold)},supportingContent={Text(subtitle)},leadingContent={Icon(icon,null,tint=Coral)},trailingContent={Icon(Icons.Outlined.OpenInNew,null)})}}
@Composable private fun Title(t:String)=Text(t,fontWeight=FontWeight.Black,fontSize=23.sp,color=Ink,modifier=Modifier.semantics{heading()})
@Composable private fun Notice(t:String)=Surface(color=Gold.copy(.18f),shape=RoundedCornerShape(16.dp)){Row(Modifier.padding(16.dp)){Icon(Icons.Outlined.Info,null,tint=Coral);Spacer(Modifier.width(12.dp));Text(t,color=Ink)}}
@Composable private fun Empty(t:String)=Text(t,Modifier.fillMaxWidth().padding(vertical=24.dp),color=Ink.copy(.65f))
@Composable private fun ScrollChips(values:List<String>,selected:String,onSelect:(String)->Unit)=Row(Modifier.horizontalScroll(androidx.compose.foundation.rememberScrollState()),horizontalArrangement=Arrangement.spacedBy(8.dp)){values.forEach{FilterChip(selected=it==selected,onClick={onSelect(it)},label={Text(it)})}}
private fun open(context:android.content.Context,url:String){runCatching{context.startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))}}
