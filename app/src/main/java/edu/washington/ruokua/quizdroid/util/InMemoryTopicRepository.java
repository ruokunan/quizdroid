package edu.washington.ruokua.quizdroid.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by ruokua on 5/8/15.
 */
public class InMemoryTopicRepository implements TopicRepository {
    private Topic currentTopic;
    private Topic mathTopic;
    private Topic physicsTopic;
    private Topic marvelTopic;
    private QuestionList questionList;

    //title of the topic
    private final String MATH_TITLE = "Math";

    private final String PHYSICS_TITLE = "Physics";

    private final String MARVEL_TITLE = "Marvel";


    //shor  description of the topic

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


    public InMemoryTopicRepository() {
            questionList = new QuestionList();
    }




    /**
     * 
     */
    public void updateCurrentTopic(String topic) {
        switch (topic) {
            case "Math":
                if(mathTopic == null) {
                    setMathTopic();
                }
                currentTopic = mathTopic;
            case "Physics":
                if(physicsTopic == null) {
                    setPhysicsTopic();
                }
                currentTopic = physicsTopic;
            default:
                if(marvelTopic == null) {
                    setMarvelTopic();
                }
                currentTopic = marvelTopic;
        }
    }

    public void setMathTopic() {
        this.mathTopic = new Topic(MATH_TITLE, MATH_SHORT_DESC,MATH_LONG_DESC,
                questionList.getCurrentQuestions(MATH_TITLE));
    }

    public void setPhysicsTopic() {
        this.physicsTopic =  new Topic(PHYSICS_TITLE,PHYSICS_SHORT_DESC,PHYSICS_LONG_DESC,
                questionList.getCurrentQuestions(PHYSICS_TITLE));;
    }

    public void setMarvelTopic() {
        this.marvelTopic = new Topic(MARVEL_TITLE,MARVEL_SHORT_DESC,MARVEL_LONG_DESC,
                questionList.getCurrentQuestions(MARVEL_TITLE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return currentTopic.getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortDesc() {
        return currentTopic.getShortDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLongDesc() {
        return currentTopic.getLongDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumQuestionContain() {
        return currentTopic.getNumQuestionContain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurSelect() {
        return currentTopic.getCurSelect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurSelect(int curSelect) {
        currentTopic.setCurSelect(curSelect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore() {
        currentTopic.addScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return currentTopic.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuestionNum() {
        return currentTopic.getQuestionNum();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void nextQuestion() {
        currentTopic.nextQuestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNextQuestion() {
        return currentTopic.hasNextQuestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Question getCurrentQuestion() {
        return currentTopic.getCurrentQuestion();
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
                case "Math":
                    if (marvelQuestions == null) {
                        setMathQuestions();
                    }

                    return marvelQuestions;
                case "Physics":
                    if (physicsQuestions == null) {
                        setPhysicsQuestions();
                    }
                    return physicsQuestions;
                default:
                    if (mathQuestions == null) {
                        setMarvelQuestions();
                    }
                    return marvelQuestions;
            }

        }

        /**
         * initialize a list of math questions
         */
        private void setMathQuestions(){
            mathQuestions = new ArrayList<>();
            List<String> desc = new ArrayList<>(Arrays.asList(
                    "1 + 1 = ?",
                    "2 * 3 = ?",
                    "10 % 10 = ?"));
            List<List<String>> options = asList(
                    asList("1", "2", "3", "4"),
                    asList("3", "4", "5", "6"),
                    asList("0", "1", "2", "3")
            );
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
            List<String> desc = new ArrayList<>(Arrays.asList(
                    "Acceleration of an object due to gravity?",
                    "Acceleration of an object due to gravity in the moon?",
                    "Acceleration of an object due to gravity in the bible?"));
            List<List<String>> options = asList(
                    asList("9.8 m/s/s", "10 mi/s", "1 in/s", "Magic"),
                    asList("9.8 m/s/s", "1.622 m/s/s10 mi/s", "1 in/s", "I do not know"),
                    asList("guess", "make a guess", "there is no god", "goD bless me")
            );
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
