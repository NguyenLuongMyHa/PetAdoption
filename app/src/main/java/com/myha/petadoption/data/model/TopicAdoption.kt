package com.myha.petadoption.data.model

data class TopicAdoption(val topicName: String, val adoptions: List<Adoption>) {
}

/*
Adoption
id
fee (null - free)
status
type (urgen, waiting, adopted)
thumnail
active (bool)
start_date
end_date
pets (Array Pet)
contract_condition (Array Condition)
process_condition (Array Process)
view_count (track views -> Hot List)
interest_count (track click adopt -> Wish List)
 */