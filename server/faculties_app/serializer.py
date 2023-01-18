from rest_framework import fields, serializers
from .models import Faculty, Department

class FacultySerializer(serializers.ModelSerializer):
    class Meta:
        model  = Faculty
        fields = '__all__'



class DepartmentSerializer(serializers.ModelSerializer):
    tour = FacultySerializer
    class Meta:
        model  = Department
        fields = '__all__'