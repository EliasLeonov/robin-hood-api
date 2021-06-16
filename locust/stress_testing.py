from random import choice
from string import ascii_lowercase

from locust import HttpUser, task, between


class User(HttpUser):

    def __init__(self, parent):
        super(User, self).__init__(parent)
        self.token = ""

    wait_time = between(1, 2)

    def on_start(self):
        random_username = ''.join(choice(ascii_lowercase) for i in range(20))

        create_user_data = {
            "email": "randomemail@gmail.com",
            "lastname": "User",
            "name": "Random",
            "password": "superSecurePassword",
            "username": random_username
        }

        login_user_data = {
            "password": "superSecurePassword",
            "username": random_username
        }

        self.client.post(url="/register", json=create_user_data)
        with self.client.post(url="/login", json=login_user_data) as response:
            self.token = response.json()["jwt"]

    @task
    def get_transactions(self):
        self.client.get(
            url="/get-all",
            headers={"authorization": "Bearer " + self.token},
            name="Get Transactions"
        )

    @task
    def post_transaction(self):
        post_body = {
            "amount": 0,
            "price": 126.74,
            "tickerDto": {
                "companyName": "Apple Inc.",
                "id": 3,
                "tickerName": "AAPL"
            }
        }

        self.client.post(
            url="/transaction",
            json=post_body,
            headers={"authorization": "Bearer " + self.token},
            name="Create AAPL Transactions"
        )
