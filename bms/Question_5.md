Design schema for Udemy

Requirements:

1. Instructors can publish their courses by uploading all the course related lectures on the platform.
2. Each course has multiple modules and each module has multiple lectures.
3. Each lecture will have a recorded video, but can also have file attachments as reference materials or links as reading materials.
4. An instructor can publish multiple such courses. A course can be published by multiple instructors.
5. An user can buy multiple of these courses and complete them to gain knowledge.
6. We want to have a feature where user can start a lecture from where he last dropped off. E.g. previously if the learner has watched 10 mins of a 30 mins long lecture, then next time when the user logs back in we should give him an option to resume the same video from the 10 mins mark.
8. Users can rate courses.

Expected solution:

instructors(id, name, description)
instructor_courses(instructor_id, course_id)
courses(id, name. description, price)
modules(id, course_id, sequence_no, name, description)
lectures(id, module_id, sequence_no, name, description, video_url, duration_in_secs)
lecture_reading_links(id, lecture_id, link)
lecture_ref_material_links(id, lecture_id, link)
user(id, name, email, phone)
purchased_courses(id, user_id, course_id, bought_at, user_rating)
watch_history(id, user_id, lecture_id, completed_duration_in_secs, updated_at)




