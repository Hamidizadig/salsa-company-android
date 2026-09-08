package de.salsacompany.app.data

import de.salsacompany.app.model.*

/** UI only depends on this contract. Replace BundledContentRepository with a CMS implementation later. */
interface ContentRepository {
    fun branches(): List<Branch>
    fun classes(branchId:String): List<DanceClass>
    fun prices(branchId:String): List<Price>
    fun teachers(): List<Teacher>
    fun events(): List<EventLink>
}

class BundledContentRepository : ContentRepository {
    override fun branches() = OfficialContent.branches
    override fun classes(branchId:String) = OfficialContent.classes.filter { it.branchId == branchId }
    override fun prices(branchId:String) = if(branchId == "stuttgart") OfficialContent.prices else emptyList()
    override fun teachers() = OfficialContent.teachers
    override fun events() = OfficialContent.events
}
