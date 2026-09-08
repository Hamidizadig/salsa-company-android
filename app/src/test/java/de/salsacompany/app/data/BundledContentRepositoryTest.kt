package de.salsacompany.app.data

import org.junit.Assert.*
import org.junit.Test

class BundledContentRepositoryTest {
    private val repository = BundledContentRepository()
    @Test fun `official branch ids are unique`() = assertEquals(repository.branches().size, repository.branches().map { it.id }.distinct().size)
    @Test fun `all URLs are HTTPS`() {
        val urls=repository.branches().map{it.url}+repository.events().map{it.url}+repository.classes("stuttgart").map{it.bookingUrl}
        assertTrue(urls.all { it.startsWith("https://") })
    }
    @Test fun `every class references a branch`() {
        val ids=repository.branches().map{it.id}.toSet()
        assertTrue(repository.classes("stuttgart").all{it.branchId in ids})
    }
}
