package release

import java.util.regex.Matcher
import org.gradle.api.Project

/**
 * @author elberry
 * Created: Tue Aug 09 15:32:00 PDT 2011
 */
class ReleasePluginConvention {
	boolean failOnSnapshotDependencies = true
	boolean failOnUnversionedFiles = true
	String preTagCommitMessage = "Gradle Release Plugin - pre tag commit."
	String tagCommitMessage = "Gradle Release Plugin - tagging commit."
	String newVersionCommitMessage = "Gradle Release Plugin - new version commit."
	def requiredTasks = []
	def versionPatterns = [
			/(.*[^\d])(\d*)/: { Project project, Matcher matcher ->
				String tag = matcher.group(0)
				int lastDigit = matcher.group(2) as Integer
				matcher.replaceAll("\$1${lastDigit + 1}")
			},
			/(\d+)/: { Project project, Matcher matcher ->
				String tag = matcher.group(0)
				int lastDigit = matcher.group(1) as Integer
				"${lastDigit + 1}"
			}
	]
	
	void release(Closure closure) {
		closure.delegate = this
		closure.call()
	}
}