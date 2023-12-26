Design schema for Scaler

Design schema for Scaler with the following requirements:

1. Learners are grouped together in batches.
2. Lectures are scheduled for batches and are taken by a instructor.
3. Each batch runs on a schedule like MWF Morning (lectures happen on Monday-Wednesday-Friday at 7:00 AM), TTS Evening (lecutures happen on Tuesday-Thursday-Saturday at 9:00 PM) etc.
4. Lectures are scheduled as per a batch's schedule.
5. After every lecture ends, students rate the lecture, this rating is used to calculate an instructors avg rating.
6. A learner can move to changes batches in between via course pause or other means. Thus a learner can have multiple previous batches but at max 1 current batch. We need to track learners entry and exit dates for all the previous batches so that we only show them those class's recordings which they have actually attended or they were supposed to attend. 

Expected solution:

learners(id, name, email, phone)
batches(id, name, schedule, started_at)
learner_batches(id, learner_id, batch_id, start_date, end_date)
instructors(id, name, email)
lectures(id, name, description)
scheduled_lectures(id, lecture_id, batch_id, instructor_id, lecture_start_date_time, lecture_end_date_time, lecture_link)
learner_scheduled_lecture_ratings(id, learner, lecture, rating)