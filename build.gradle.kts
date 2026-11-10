import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
	kotlin("jvm") version "2.3.21" apply false
	kotlin("plugin.spring") version "2.3.21" apply false
	id("org.springframework.boot") version "4.1.2" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
	group = "es.parkingmanager"
	version = "1.0.0-SNAPSHOT"
}

subprojects {
	repositories {
		mavenCentral()
	}

	pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
		extensions.configure<KotlinJvmProjectExtension> {
			jvmToolchain(21)

			compilerOptions {
				freeCompilerArgs.addAll(
					"-Xjsr305=strict",
					"-Xannotation-default-target=param-property"
				)
			}
		}

		tasks.withType<Test>().configureEach {
			useJUnitPlatform()
		}
	}
}