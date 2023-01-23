package dev.pgjbz.urlshorter.archetecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = ArchitectureTest.BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    public static final String BASE_PACKAGE = "dev.pgjbz.urlshorter";
    private static final String DOMAIN = "domain";
    private static final String DOMAIN_PACKAGE = "..domain..";
    private static final String INFRA = "infra";
    private static final String INFRA_PACKAGE = "..infra..";
    private static final String APP = "app";
    private static final String APP_PACKAGE = "..app..";
    private static final String JAVA_PACKAGE = "java..";

    @ArchTest
    public static final ArchRule shouldBeCleanArch = layeredArchitecture()
            .consideringAllDependencies()
            .layer(DOMAIN).definedBy(DOMAIN_PACKAGE)
            .layer(INFRA).definedBy(INFRA_PACKAGE)
            .layer(APP).definedBy(APP_PACKAGE)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APP, INFRA)
            .whereLayer(APP).mayNotBeAccessedByAnyLayer()
            .whereLayer(INFRA).mayOnlyBeAccessedByLayers(APP);

    @ArchTest
    public static final ArchRule domainOnlyAccessJavaStdLib = classes().that().resideInAPackage(DOMAIN_PACKAGE)
            .should().onlyAccessClassesThat().resideInAnyPackage(JAVA_PACKAGE, DOMAIN_PACKAGE);
}
