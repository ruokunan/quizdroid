package edu.washington.ruokua.quizdroid.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.util.Question;
import edu.washington.ruokua.quizdroid.util.Topic;

import static java.util.Arrays.asList;

/**
 * Created by ruokua on 5/8/15.
 * {@inheritDoc}
 * This Topic Repository stay in the memory
 */
public class InMemoryTopicRepository implements TopicRepository {
    //title of the topic
    private final String MATH_TITLE = "Math";
    private final String PHYSICS_TITLE = "Physics";
    private final String MARVEL_TITLE = "Marvel";


    //short  description of the topic
    private final String MATH_SHORT_DESC = "Short Description for Math ";
    private final String PHYSICS_SHORT_DESC = "Short Description for PHYSICS";
    private final String MARVEL_SHORT_DESC = "Short Description for MARVEL";

    //long description of the topic
    private final String MATH_LONG_DESC = "The abstract science of number, quantity, " +
            "and space. Mathematics may be studied in its own right ( pure Mathematics )," +
            "or as it is applied to other disciplines such as Physics and " +
            "engineering ( applied Mathematics ).";
    private final String PHYSICS_LONG_DESC = "The branch of science concerned with the nature and " +
            "properties of matter and energy. The subject matter of Physics, distinguished from " +
            "that of chemistry and biology, includes mechanics, heat, light and other radiation, " +
            "sound, electricity, magnetism, and the structure of atoms.";

    private final String MARVEL_LONG_DESC = "The Marvel Super Heroes is an American / Canadian " +
            "animated television series starring five comic-book superheroes from Marvel Comics." +
            " The first TV series based on Marvel characters," +
            " it debuted in syndication on U.S. television in 1966. ";

    //topic object for each topic
    private Topic mathTopic;
    private Topic physicsTopic;
    private Topic marvelTopic;

    //the questionsList manage the list of question contained in each topic
    private QuestionList questionList;



    // A list of different topics on which allow user to take quiz
    private final List<Topic> inMemoryTopics = new ArrayList<>(Arrays.asList(
            new Topic.Builder(MATH_TITLE, MATH_SHORT_DESC).build(),
            new Topic.Builder(PHYSICS_TITLE, PHYSICS_SHORT_DESC)
                    .icon(R.drawable.common_signin_btn_icon_light)
                    .build(),
            new Topic.Builder(MARVEL_TITLE, MARVEL_SHORT_DESC).build()

    ));



    /**
     * @effects: initialize an InMemoryTopicRepository with
     * an empty question list
     */
    public InMemoryTopicRepository() {

        questionList = new QuestionList();

    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> getTopicList() {
        return inMemoryTopics;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public Topic getCurrentTopic(int topicIndex) {

        switch (topicIndex) {
            case 0:
                if (mathTopic == null) {
                    setMathTopic();
                }
                return mathTopic;

            case 1:
                if (physicsTopic == null) {
                    setPhysicsTopic();
                }
                return physicsTopic;

            case 2:
                if (marvelTopic == null) {
                    setMarvelTopic();
                }
                return marvelTopic;
            default:
                throw new RuntimeException("No such Topic exist");
        }
    }



    //initialize math topic
    private void setMathTopic() {
        this.mathTopic = new Topic.Builder(MATH_TITLE, MATH_SHORT_DESC)
                .longDesc(MATH_LONG_DESC)
                .questions(questionList.getCurrentQuestions(MATH_TITLE))
                .build();

    }

    //initialize Physics topic
    private void setPhysicsTopic() {
        this.physicsTopic = new Topic.Builder(PHYSICS_TITLE, PHYSICS_SHORT_DESC)
                .longDesc(PHYSICS_LONG_DESC)
                .icon(R.drawable.common_signin_btn_icon_light)
                .questions(questionList.getCurrentQuestions(PHYSICS_TITLE)).build();

    }

    //initialize marvel topic
    private void setMarvelTopic() {
        this.marvelTopic = new Topic.Builder(MARVEL_TITLE, MARVEL_SHORT_DESC)
                .longDesc(MARVEL_LONG_DESC)
                .questions(questionList.getCurrentQuestions(MARVEL_TITLE)).build();
    }


    //A Collection of collection of questions
    private class QuestionList {
        private List<Question> marvelQuestions;
        private List<Question> physicsQuestions;
        private List<Question> mathQuestions;

        //create a new list of question
        public QuestionList() {
            marvelQuestions = null;
            physicsQuestions = null;
            mathQuestions = null;
        }


        /**
         * @return the list of Question for user select topic
         */
        public List<Question> getCurrentQuestions(String topic) {
            switch (topic) {
                case MATH_TITLE:
                    if (mathQuestions == null) {
                        setMathQuestions();
                    }

                    return mathQuestions;
                case PHYSICS_TITLE:
                    if (physicsQuestions == null) {
                        setPhysicsQuestions();
                    }
                    return physicsQuestions;
                case MARVEL_TITLE:
                    if (marvelQuestions == null) {
                        setMarvelQuestions();
                    }
                    return marvelQuestions;
            }
            throw new RuntimeException("This topic is not exist");
        }

        /**
         * initialize a list of math questions
         */
        private void setMathQuestions() {
            mathQuestions = new ArrayList<>();
            //Question Description
            List<String> desc = new ArrayList<>(Arrays.asList(
                    "1 + 1 = ?",
                    "2 * 3 = ?",
                    "10 % 10 = ?"));
            //Question options List
            List<List<String>> options = asList(
                    asList("1", "2", "3", "4"),
                    asList("3", "4", "5", "6"),
                    asList("0", "1", "2", "3")
            );
            //Question Answer List
            List<Integer> answers = new ArrayList<>(Arrays.asList(
                    1,
                    3,
                    0));
            for (int i = 0; i < desc.size(); i++) {
                mathQuestions.add(new Question(desc.get(i), options.get(i), answers.get(i)));
            }

        }


        /**
         * @effects: initialize a list of Question of Physics
         */
        private void setPhysicsQuestions() {
            physicsQuestions = new ArrayList<>();
            //Question Description
            List<String> desc = new ArrayList<>(Arrays.asList(
                    "Acceleration of an object due to gravity?",
                    "Acceleration of an object due to gravity in the moon?",
                    "Acceleration of an object due to gravity in the bible?"));
            //Question options List
            List<List<String>> options = asList(
                    asList("9.8 m/s/s", "10 mi/s", "1 in/s", "Magic"),
                    asList("9.8 m/s/s", "1.622 m/s/s10 mi/s", "1 in/s", "I do not know"),
                    asList("guess", "make a guess", "there is no god", "goD bless me")
            );
            //Question Answer List
            List<Integer> answers = new ArrayList<>(Arrays.asList(
                    0,
                    1,
                    2));
            for (int i = 0; i < desc.size(); i++) {
                physicsQuestions.add(new Question(desc.get(i), options.get(i), answers.get(i)));
            }

        }

        /**
         * initialize a list of question of Marvel art
         */
        private void setMarvelQuestions() {
            marvelQuestions = new ArrayList<>();
            //Question Description
            List<String> desc = new ArrayList<>(Arrays.asList(
                    "The name of batman?",
                    "The name of superman",
                    "The name of batman's car"));
            //Question options List
            List<List<String>> options = asList(
                    asList("batman for sure", "bat-man", "batttttman", "select this, this is right"),
                    asList("I do not know", "SuperMan",
                            "Eric Chee "
                            , "Me"),
                    asList("BatMan Dragster", "Audi", "BMW", "Mustang")
            );
            //Question Answer List
            List<Integer> answers = new ArrayList<>(Arrays.asList(
                    1,
                    1,
                    0));
            for (int i = 0; i < desc.size(); i++) {
                marvelQuestions.add(new Question(desc.get(i), options.get(i), answers.get(i)));
            }

        }
    }
}

