// adp is the examined AttributeDataProfile object
val abstractType: AbstractDataType.Value = basicDataType match {
case BasicDataType.String =>
if(adp.stats(StatsType.TokenCounts).mean >= 10){
AbstractDataType.Text
}else if(adp.stats(StatsType.TokenCounts).mean >= 1.2){
AbstractDataType.WordSequence
}else{
AbstractDataType.Word
}
case BasicDataType.Int =>
if(adp.stats(StatsType.AttributeValues).min > 1500 &&
adp.stats(StatsType.AttributeValues).max < 2500){
AbstractDataType.Year
}else{
null // dummy: space to add more logic...
}
case BasicDataType.Double =>
null // dummy: space to add more logic...
case BasicDataType.Boolean =>
null // dummy: space to add more logic...
case _ =>
null
}
