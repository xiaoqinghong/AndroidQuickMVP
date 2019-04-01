<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>

	

    <#if isFragment>
        <instantiate from="root/src/app_package/MvpFragment.kt.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(fragmentPackageName)}/${pageName}Fragment.kt" />

        <instantiate from="root/res/layout/simple.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${fragmentLayoutName}.xml" />
    </#if>

    <#if !isFragment>
        <instantiate from="root/src/app_package/MvpActivity.kt.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(ativityPackageName)}/${pageName}Activity.kt"  />

        <instantiate from="root/res/layout/simple.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${activityLayoutName}.xml" />
    </#if>

    <instantiate from="root/src/app_package/MvpModel.kt.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(modelPackageName)}/${pageName}Model.kt" />

	<instantiate from="root/src/app_package/MvpContract.kt.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(contractPackageName)}/${pageName}Contract.kt" />
				   
	<instantiate from="root/src/app_package/MvpPresenterImpl.kt.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(presenterPackageName)}/${pageName}PresenterImpl.kt" />
	
	<#if !isFragment>
        <merge from="root/AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    </#if>
</recipe>
