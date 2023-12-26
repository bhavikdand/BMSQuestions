Design class diagram for Scaler

Design class diagram for Scaler with the following requirements:

1. Learners are grouped together in batches.
2. Lectures are scheduled for batches and are taken by a instructor.
3. Each batch runs on a schedule like MWF Morning (lectures happen on Monday-Wednesday-Friday at 7:00 AM), TTS Evening (lecutures happen on Tuesday-Thursday-Saturday at 9:00 PM) etc.
4. Lectures are scheduled as per a batch's schedule.
5. After every lecture ends, students rate the lecture, this rating is used to calculate an instructors avg rating.
6. A learner can move to changes batches in between via course pause or other means. Thus a learner can have multiple previous batches but at max 1 current batch. We need to track learners entry and exit dates for all the previous batches so that we only show them those class's recordings which they have actually attended or they were supposed to attend. 

Expected solution:

class BaseModel
	int id

class Learner extends BaseModel
	String name
	String email
	String phone

class Batch extends BaseModel
	String name
	Schedule schedule
	Date startedAt

enum Schedule
	TTS_MORNING, TTS_EVENING, MWF_MORNING, MWF_EVENING

class LearnerBatches extends BaseModel
	Learner learner
	Batch batch
	Date startDate
	Date endDate

class Instructor extends BaseModel
	String name
	String email

class Lecture extends BaseModel
	String name
	String description

class ScheduledLecture extends BaseModel
	Lecture lecture
	Batch batch
	Instructor instructor
	Date lectureStartDateTime
	Date lectureEndDateTime
	String lectureLink

class LearnerScheduledLectureRating extends BaseModel
	Learner learner
	ScheduledLecture lecture
	int rating
