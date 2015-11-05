package jmssample


class TestController {
    def testService
    def index() {
        testService.testJMS()

        render(view: "index")
    }

    def scaffold = true

    def save = {
        testService.testJMS()
        flash.message = "Message queued for persistence"
        redirect(action: "list")
    }

}
