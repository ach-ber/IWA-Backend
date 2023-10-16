from faker import Faker
from uuid_extensions import uuid7str
import json
import sys

fake = Faker("fr_FR")


def fake_address():
    return {
        "streetNum": fake.building_number(),
        "streetName": fake.street_name(),
        # "complement": fake.secondary_address(), TODO
        "zipCode": fake.postcode(),
        "city": fake.city(),
        "country": fake.country(),
    }


def fake_reference():
    return {
        "refName": fake.name(),
        "refEstablishment": fake.company(),
        "refAddress": fake_address(),
        "refPhone": fake.phone_number(),
        "refEmail": fake.email(),
    }


def fake_period_past():
    """fake a period in the past"""
    startedAt = fake.date_time_this_year(before_now=True, after_now=False)
    endedAt = fake.date_time_between(start_date=startedAt, end_date="now")
    return [startedAt, endedAt]


def fake_experience():
    startedAt, endedAt = fake_period_past()
    return {
        "job": fake.job(),
        "jobCategory": fake.random_int(min=1, max=20),  # TODO: use real categories?
        "startedAt": startedAt.isoformat(),
        "endedAt": endedAt.isoformat(),
        "establishment": {
            "establishmentName": fake.company(),
            "establishmentAddress": fake_address(),
        },
    }


def fake_availability():
    return {
        "job": fake.job(),
        "jobCategory": fake.random_int(min=1, max=20),
        "startsAt": fake.date_time_this_year(
            before_now=True, after_now=False
        ).isoformat(),
        "endsAt": fake.date_time_this_year(
            before_now=False, after_now=True
        ).isoformat(),
        "places": [fake_address() for _ in range(fake.random_int(min=1, max=5))],
    }


def fake_opinion():
    return {
        "score": fake.random_int(min=0, max=5),
        "message": fake.text(),
        "employerId": uuid7str(),
        "providedAt": fake.date_time_this_year(
            before_now=True, after_now=False
        ).isoformat(),
    }


def fake_candidate():
    return {
        "id": uuid7str(),
        "firstname": fake.first_name_male(),
        "lastname": fake.last_name(),
        "gender": fake.random_element(elements=[1, 2]),
        "birthDate": fake.date_time_this_century().strftime("%Y-%m-%d"),
        "citizenship": fake.country(),
        "address": fake_address(),
        "email": fake.email(),
        "phone": fake.phone_number(),
        "photo": fake.image_url(),
        "cv": fake.url(),
        "shortBio": fake.text(),
        "references": [fake_reference() for _ in range(fake.random_int(min=0, max=5))],
        "experiences": [
            fake_experience() for _ in range(fake.random_int(min=0, max=5))
        ],
        "availabilities": [
            fake_availability() for _ in range(fake.random_int(min=1, max=5))
        ],
        "opinions": [fake_opinion() for _ in range(fake.random_int(min=0, max=5))],
    }


def generate_candidates(number=1):
    return [fake_candidate() for _ in range(number)]


data = {"candidats": generate_candidates(300)}

output_path = "fake_candidates.json"

if len(sys.argv) > 1:
    output_path = sys.argv[1]

with open(output_path, "w") as f:
    json.dump(data, f, indent=4)
