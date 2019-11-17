package br.com.joaopaulosj.vanhackathon2019.data.remote.models

class JobsResponse(
		val result: List<JobResponse> = emptyList()
)

class JobResponse(
		val id: Int = 0,
		val mustHaveSkills: List<SkillResponse> = emptyList(),
		val niceToHaveSkills: List<SkillResponse> = emptyList(),
		val positionName: String = "",
		val description: String = "",
		val descriptionHtml: String = "",
		val city: String = "",
		val country: String = "",
		var applied: Boolean = false,
		var favorited: Boolean = false,
		var newJob: Boolean = false,
		val salaryRangeStart: Int = 0,
		val salaryRangeEnd: Int = 0,
		val matchPorcentage: Float = 0f
) {
	
	fun getFlagResName(): String{
		return "flag_" + country.replace(" ", "").toLowerCase()
	}
	
	fun getAllSkills(): List<SkillResponse>{
		mustHaveSkills.forEach { it.mustHave = true }
		return mustHaveSkills + niceToHaveSkills
	}
	
}

class SkillResponse(
		val name: String = "",
		val match: Boolean = false,
		var mustHave: Boolean = false
)