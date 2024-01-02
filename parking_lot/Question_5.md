Design class diagram for Udemy

Requirements:

1. Instructors can publish their courses by uploading all the course related lectures on the platform.
2. Each course has multiple modules and each module has multiple lectures.
3. Each lecture will have a recorded video, but can also have file attachments as reference materials or links as reading materials.
4. An instructor can publish multiple such courses. A course can be published by multiple instructors.
5. An user can buy multiple of these courses and complete them to gain knowledge.
6. We want to have a feature where user can start a lecture from where he last dropped off. E.g. previously if the learner has watched 10 mins of a 30 mins long lecture, then next time when the user logs back in we should give him an option to resume the same video from the 10 mins mark.
8. Users can rate courses.

Expected solution:

class BaseModel
	int id

class Instructor extends BaseModel
	String name
	String description
	List<Course> courses

	
class Course extends BaseModel
	String name
	String description
	List<Module> modules
	double price

class Module extends BaseModel
	List<Lecture> lectures
	int sequenceNumber
	String name
	String description

class Lecture extends BaseModel
	int sequenceNumber
	String name
	String description
	String videoUrl
	List<String> readlingLinks
	List<String> readlingMaterialFileUrls
	int durationInSecs

class User extends BaseModel
	String name
	String email
	String phone

class PurchasedCourse extends BaseModel
	Course course
	User user
	Date boughtAt
	int userRating

class WatchHistory extends BaseModel
	User user
	Lecture lecture
	int completedDurationInSeconds
	Date updatedAt





