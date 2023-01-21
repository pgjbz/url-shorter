package dev.pgjbz.urlshorter.archetecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "dev.pgjbz.urlshorter", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    private static final String BASE_PACKAGE = "dev.pgjbz.urlshorter";
    private static final String DOMAIN = "domain";
    private static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".domain..";
    private static final String INFRA = "infra";
    private static final String INFRA_PACKAGE = BASE_PACKAGE + ".infra..";
    private static final String APP = "app";
    private static final String APP_PACKAGE = BASE_PACKAGE + ".app..";

    @ArchTest
    public static final ArchRule shouldBeCleanArch = layeredArchitecture()
            .consideringAllDependencies()
            .layer(DOMAIN).definedBy(DOMAIN_PACKAGE)
            .layer(INFRA).definedBy(INFRA_PACKAGE)
            .layer(APP).definedBy(APP_PACKAGE)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APP, INFRA)
            .whereLayer(APP).mayNotBeAccessedByAnyLayer()
            .whereLayer(INFRA).mayOnlyBeAccessedByLayers(APP);

}
