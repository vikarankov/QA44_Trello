package tests;

import dto.BoardDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

public class BoardsTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("z0559882272@gmail.com")
            .password("Mmar123456$")
            .build();


    @Test
    public void createBoardPositive() {
        //int i = (int) ((System.currentTimeMillis()/1000)%3600);
        int i = new Random().nextInt(1000) + 1000;

        BoardDTO board = BoardDTO.builder()
                .boardTitle("QA44-"+i)
                .build();
        HomePage homePage = new HomePage(getDriver());
       Assert.assertTrue(homePage.clickBtnLogin()
               .typeEmail(user)
               .typePassword(user)
               .typeBoardTitle(board)
               .clickBtnCreateSubmitPositive()
               .isTextInElementPresent_nameBoard(board.getBoardTitle())); ;

    }

    @Test
    public void createBoardNegative() {

        BoardDTO board = BoardDTO.builder()
                .boardTitle("   ")
                .build();
        HomePage homePage = new HomePage(getDriver());
        Assert.assertFalse(homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user)
                .typeBoardTitle(board)
                .clickBtnCreateSubmitNegative()
                .isElementClickable_btnCreateSubmit(),"element is clickable");

    }
}
